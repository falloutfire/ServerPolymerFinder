package com.kleknerpolymer.server.service

import com.kleknerpolymer.server.entity.Polymer
import java.util.*

interface PolymerService {
    fun addPolymer(polymer: Polymer)
    fun deletePolymer(id: Long)
    fun updatePolymer(polymer: Polymer)
    fun allPolymer(): List<Polymer>
    fun addBlends(id: Long, blends: Set<Long>)
    fun getPolymerById(id: Long): Optional<Polymer>
    fun getBlendsById(id: Long): List<Polymer>
}