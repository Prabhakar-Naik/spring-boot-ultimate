package com.springboot.ultimate.colors.rawimpl;

import com.springboot.ultimate.colors.raw.BluePrinter;
import org.springframework.stereotype.Component;

/**
 * @author prabhakar, @Date 24-07-2024
 */
@Component
public class EnglishBluePrinter implements BluePrinter {

    @Override
    public String print() {
        return "Blue";
    }
}
