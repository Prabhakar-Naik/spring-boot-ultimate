package com.springboot.ultimate.colors.rawimpl;

import com.springboot.ultimate.colors.raw.RedPrinter;
import org.springframework.stereotype.Component;

/**
 * @author prabhakar, @Date 24-07-2024
 */
@Component
public class EnglishRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "Red";
    }
}
