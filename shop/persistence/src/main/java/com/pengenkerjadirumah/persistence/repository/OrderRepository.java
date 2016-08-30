/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengenkerjadirumah.persistence.repository;

import com.pengenkerjadirumah.persistence.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT o FROM Order o WHERE o.reference LIKE %?1% AND o.customer.tenant.id=?2",
            countQuery = "SELECT count(o) FROM Order o WHERE o.reference LIKE %?1% AND o.customer.tenant.id=?2")
    Page<Order> findOrders(String keywords, long tenantId, Pageable pageable);
}
