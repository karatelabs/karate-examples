package examples;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

import java.util.HashMap;
import java.util.Map;

public class DynamoDbUtils {

    final DynamoDbClient db;

    public DynamoDbUtils() {
        db = DynamoDbClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create("myAwsProfile"))
                .region(Region.US_EAST_2)
                .build();
    }

    public Map<String, Object> getItem(String keyValue) {
        Map<String, AttributeValue> keyToGet = new HashMap<>();
        keyToGet.put("myPrimaryKeyName", AttributeValue.builder().s(keyValue).build());
        // keyToGet.put("mySecondaryKeyName", AttributeValue.builder().s(keyValue).build());
        GetItemRequest get = GetItemRequest.builder().key(keyToGet).tableName("myTableName").build();
        GetItemResponse response = db.getItem(get);
        if (!response.hasItem()) {
            System.out.println("item not found by key: " + keyValue);
            return null;
        }
        Map<String, AttributeValue> row = response.item();
        Map<String, Object> result = new HashMap<>();
        result.put("myFieldName", row.get("myFieldName").s());
        return result;
    }

}
