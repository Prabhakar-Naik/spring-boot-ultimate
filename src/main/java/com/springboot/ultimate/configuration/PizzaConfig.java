package com.springboot.ultimate.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author prabhakar, @Date 25-07-2024
 */
@Configuration
@ConfigurationProperties(prefix = "pizza")
public class PizzaConfig {

    private String sauce;
    private String topping;
    private String crust;

    public PizzaConfig() {
    }

    public PizzaConfig(String sauce, String topping, String crust) {
        this.sauce = sauce;
        this.topping = topping;
        this.crust = crust;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    @Override
    public String toString() {
        return "PizzaConfig{" +
                "source='" + sauce + '\'' +
                ", topping='" + topping + '\'' +
                ", crust='" + crust + '\'' +
                '}';
    }
}
