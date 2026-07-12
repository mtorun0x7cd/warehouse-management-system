/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wawi.datenhaltung.wawidbmodel.exception;

/**
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
public class NoEntityManagerException extends RuntimeException
{
    public NoEntityManagerException()
    {
        super("Kein EntityManager gesetzt");
    }
    
}
