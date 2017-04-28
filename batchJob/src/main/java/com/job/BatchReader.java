package com.job;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Arrays;

/**
 * Created by jinqiang.liu on 17-4-27.
 */
public class BatchReader<T> implements ItemReader<T> {

    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
