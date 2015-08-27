package net.chrisrichardson.getataxi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GetATaxiObjectMapperConfigurer {

  public static void configureObjectMapper(ObjectMapper om) {
    om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    om.setVisibilityChecker(om.getSerializationConfig().getDefaultVisibilityChecker()
            .withFieldVisibility(JsonAutoDetect.Visibility.ANY).
                    withGetterVisibility(JsonAutoDetect.Visibility.NONE).
                    withSetterVisibility(JsonAutoDetect.Visibility.NONE));
  }
}
