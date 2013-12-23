package com.gmail.jameshealey1994.breakout;

/**
 * Class used to define objects used to synchronise threads.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Lock {
    
    /**
     * Static object to be used as a LOCK to synchronise threads.
     */
    public static final Lock LOCK = new Lock();
}