package kr.mythings.ds.mychef.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="storage")
@Getter @Setter
public class StorageConfig {

     private String path;


}
