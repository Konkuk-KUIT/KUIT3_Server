package kuit.server.dao;

import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.dto.store.PutStoreRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class StoreDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StoreDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    // 새로운 Store 생성
    public long createStore(PostStoreRequest postStoreRequest) {
        String sql = "INSERT INTO stores(name, type, category, address, storePictureUrl, phone, content, minDeliveryPrice, deliveryTip, minDeliveryTime, maxDeliveryTime, operationHours, closedDays, deliveryAddress, createdDate, modifiedDate) " +
                "VALUES(:name, :type, :category, :address, :storePictureUrl, :phone, :content, :minDeliveryPrice, :deliveryTip, :minDeliveryTime, :maxDeliveryTime, :operationHours, :closedDays, :deliveryAddress, NOW(), NOW())";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postStoreRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    // Store 조회
    public GetStoreResponse getStoreById(long storeId) {
        String sql = "SELECT * FROM stores WHERE storeId = :storeId";
        Map<String, Object> param = Map.of("storeId", storeId);
        return jdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<>(GetStoreResponse.class));
    }

    // 모든 Store 조회
    public List<GetStoreResponse> getAllStores() {
        String sql = "SELECT * FROM stores";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetStoreResponse.class));
    }

    // 카테고리가 Food인 스토어들을 조회하는 메서드
    public List<GetStoreResponse> getStoresByCategory(String category) {
        String sql = "SELECT * FROM stores WHERE category = :category";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("category", category);
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(GetStoreResponse.class));
    }

    // Store 정보 수정
    public int updateStore(long storeId, PutStoreRequest putStoreRequest) {
        String sql = "UPDATE stores SET name = :name, type = :type, category = :category, address = :address, storePictureUrl = :storePictureUrl, phone = :phone, content = :content, minDeliveryPrice = :minDeliveryPrice, deliveryTip = :deliveryTip, minDeliveryTime = :minDeliveryTime, maxDeliveryTime = :maxDeliveryTime, rating = :rating, dibsCount = :dibsCount, reviewCount = :reviewCount, operationHours = :operationHours, closedDays = :closedDays, deliveryAddress = :deliveryAddress, modifiedDate = NOW(), status = :status WHERE storeId = :storeId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("storeId", storeId)
                .addValue("name", putStoreRequest.getName())
                .addValue("type", putStoreRequest.getType())
                .addValue("category", putStoreRequest.getCategory())
                .addValue("address", putStoreRequest.getAddress())
                .addValue("storePictureUrl", putStoreRequest.getStorePictureUrl())
                .addValue("phone", putStoreRequest.getPhone())
                .addValue("content", putStoreRequest.getContent())
                .addValue("minDeliveryPrice", putStoreRequest.getMinDeliveryPrice())
                .addValue("deliveryTip", putStoreRequest.getDeliveryTip())
                .addValue("minDeliveryTime", putStoreRequest.getMinDeliveryTime())
                .addValue("maxDeliveryTime", putStoreRequest.getMaxDeliveryTime())
                .addValue("rating", putStoreRequest.getRating())
                .addValue("dibsCount", putStoreRequest.getDibsCount())
                .addValue("reviewCount", putStoreRequest.getReviewCount())
                .addValue("operationHours", putStoreRequest.getOperationHours())
                .addValue("closedDays", putStoreRequest.getClosedDays())
                .addValue("deliveryAddress", putStoreRequest.getDeliveryAddress())
                .addValue("status", putStoreRequest.getStatus());

        return jdbcTemplate.update(sql, param);
    }

    // Store 삭제
    public int deleteStore(long storeId) {
        String sql = "DELETE FROM stores WHERE storeId = :storeId";
        Map<String, Object> param = Map.of("storeId", storeId);
        return jdbcTemplate.update(sql, param);
    }
}
