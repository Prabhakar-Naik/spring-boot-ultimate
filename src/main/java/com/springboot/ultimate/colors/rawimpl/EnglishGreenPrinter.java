package com.springboot.ultimate.colors.rawimpl;

import com.springboot.ultimate.colors.raw.GreenPrinter;
import org.springframework.stereotype.Component;

/**
 * @author prabhakar, @Date 24-07-2024
 */
@Component
public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "Green.";
    }
}
