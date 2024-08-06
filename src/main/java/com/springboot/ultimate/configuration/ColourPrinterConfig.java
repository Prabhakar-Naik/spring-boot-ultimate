//package com.springboot.ultimate.configuration;
//
//import com.springboot.ultimate.colors.raw.BluePrinter;
//import com.springboot.ultimate.colors.raw.ColourPrinter;
//import com.springboot.ultimate.colors.raw.GreenPrinter;
//import com.springboot.ultimate.colors.raw.RedPrinter;
//import com.springboot.ultimate.colors.rawimpl.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author prabhakar, @Date 24-07-2024
// */
//@Configuration
//public class ColourPrinterConfig {
//
//    /*
//    // just we can change the configuration
//    @Bean
//    public BluePrinter bluePrinter(){
//        return new EnglishBluePrinter();
//    }
//
//    @Bean
//    public RedPrinter redPrinter(){
//        return new EnglishRedPrinter();
//    }
//
//    @Bean
//    public GreenPrinter greenPrinter(){
//        return new EnglishGreenPrinter();
//    }
//    */
//
//    @Bean
//    public BluePrinter bluePrinter(){
//        return new SpanishBluePrinter();
//    }
//
//    @Bean
//    public RedPrinter redPrinter(){
//        return new SpanishRedPrinter();
//    }
//
//    @Bean
//    public GreenPrinter greenPrinter(){
//        return new SpanishGreenPrinter();
//    }
//
//
//
//    @Bean
//    public ColourPrinter colourPrinter(BluePrinter bluePrinter, RedPrinter redPrinter, GreenPrinter greenPrinter){
//        return new ColourPrinterImpl(bluePrinter,redPrinter,greenPrinter);
//    }
//}
