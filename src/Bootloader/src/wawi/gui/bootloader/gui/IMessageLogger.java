package wawi.gui.bootloader.gui;

import wawi.gui.bootloader.grenz.Message;

/**
 * Interface for logging messages.
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
public interface IMessageLogger {
    public void logMessage(String msg, Message msgTyp);    
}
