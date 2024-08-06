package com.springboot.ultimate.colors.rawimpl;

import com.springboot.ultimate.colors.raw.BluePrinter;
import com.springboot.ultimate.colors.raw.ColourPrinter;
import com.springboot.ultimate.colors.raw.GreenPrinter;
import com.springboot.ultimate.colors.raw.RedPrinter;
import org.springframework.stereotype.Component;

/**
 * @author prabhakar, @Date 24-07-2024
 */
@Component
public class ColourPrinterImpl implements ColourPrinter {

    private final RedPrinter redPrinter;
    private final BluePrinter bluePrinter;
    private final GreenPrinter greenPrinter;

    /*
    // this is one way to declare for usage
    public ColourPrinterImpl(){
        this.redPrinter = new RedPrinterImpl();
        this.bluePrinter = new BluePrinterImpl();
        this.greenPrinter = new GreenPrinterImpl();
    }
    // or below one second way
    */

    public ColourPrinterImpl(BluePrinter bluePrinter, RedPrinter redPrinter, GreenPrinter greenPrinter){
        this.redPrinter = redPrinter;
        this.greenPrinter = greenPrinter;
        this.bluePrinter = bluePrinter;
    }



    @Override
    public String printer() {
        return String.join(",",redPrinter.print(),bluePrinter.print(),greenPrinter.print());
    }
}
