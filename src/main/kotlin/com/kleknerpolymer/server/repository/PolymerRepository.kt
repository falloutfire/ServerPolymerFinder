package com.kleknerpolymer.server.repository

import com.kleknerpolymer.server.entity.Polymer
import org.springframework.data.jpa.repository.JpaRepository

interface PolymerRepository : JpaRepository<Polymer, Long>