/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Listener;

/**
 *
 * @author CHAS
 */
public interface iObservable {
    public void newAction(Actions action);
    public void newAction(Actions action, Object data);
}
