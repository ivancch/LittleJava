package com.ivanch.littlejava.lib;

public class StringValue implements Value{
	
	
    private String value;

    public StringValue(String value) {
        this.value = value;
    }
    
    @Override
    public double asNumber() {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
//            return 0;
        	throw new NumberFormatException();
        }
    }

    @Override
    public String asString() {
        return value;
    }

    @Override
    public String toString() {
        return asString();
    }
    
}
