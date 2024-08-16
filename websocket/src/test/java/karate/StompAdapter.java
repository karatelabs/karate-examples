package karate;

import com.intuit.karate.Json;
import io.karatelabs.websocket.WebsocketAdapter;
import io.karatelabs.websocket.WebsocketConsumer;

import java.util.LinkedHashMap;
import java.util.Map;

public class StompAdapter implements WebsocketAdapter<Map<String, Object>, String> {

    @Override
    public void onMessage(WebsocketConsumer client, Map<String, Object> msg) {
        client.receive(msg);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toWire(Map<String, Object> map) {
        String command = (String) map.get("command");
        Map<String, Object> headers = (Map<String, Object>) map.get("headers");
        Object body = map.get("body");
        if (body instanceof Map) {
            body = Json.of(body).toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(command).append('\n');
        if (headers != null) {
            headers.forEach((k, v) -> {
                sb.append(k).append(':').append(v).append('\n');
            });
        }
        sb.append('\n');
        if (body != null) {
            sb.append(body);
        }
        sb.append('\0');
        return sb.toString();
    }

    @Override
    public Map<String, Object> fromWire(String text) {
        Map<String, Object> map = new LinkedHashMap<>();
        String[] lines = text.split("\\R");
        Map<String, String> headers = new LinkedHashMap<>();
        boolean headersDone = false;
        for (String line : lines) {
            if (map.isEmpty()) {
                map.put("command", line);
            } else if (line.isEmpty()) {
                map.put("headers", headers);
                headersDone = true;
            } else if ("\0".equals(line)) {
                continue;
            } else {
                if (headersDone) {
                    line = line.trim();
                    if (line.charAt(0) == '{') {
                        map.put("body", Json.of(line).asMap());
                    } else {
                        map.put("body", line);
                    }
                } else {
                    int pos = line.indexOf(':');
                    if (pos == -1) {
                        throw new RuntimeException("unexpected header: " + line);
                    }
                    String key = line.substring(0, pos);
                    String value = line.substring(pos + 1);
                    headers.put(key, value);
                }
            }
        }
        return map;
    }

}
