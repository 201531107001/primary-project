package maps;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

public class TestMaps {
    @Test
    public void test() {
        Map<String, String> map = Maps.newHashMap();
        
        Map<String, String> concurrentMap = Maps.newConcurrentMap();
    }
}
