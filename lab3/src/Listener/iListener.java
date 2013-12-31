/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Listener;

public interface iListener {
    public void addAction(iObservable caller, Actions action);
    public void addAction(iObservable caller, Actions action, Object data);
}
