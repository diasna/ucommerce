/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengenkerjadirumah.persistence.repository;

import com.pengenkerjadirumah.persistence.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */

public interface ImagesRepository extends CrudRepository<Images, Long> {
    
}
