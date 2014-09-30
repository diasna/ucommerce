/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fursel.persistence.repository;

import com.fursel.persistence.Images;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */

public interface ImagesRepository extends JpaRepository<Images, Long> {
    
}
