/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.RepositoryUsingEm;

import com.contact.contactFinderOne.entities.TableContact;
import java.util.List;

/**
 *
 * @author Amine
 */
public interface tableContactRepositoryModif {
    List<TableContact> findByRequest(String question);
}
