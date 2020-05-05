package com.kleknerpolymer.server.service.impl

import com.kleknerpolymer.server.entity.Polymer
import com.kleknerpolymer.server.repository.PolymerRepository
import com.kleknerpolymer.server.service.PolymerService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@Service
@Transactional
class PolymerServiceImpl(private val polymerRepository: PolymerRepository) : PolymerService {

    override fun addPolymer(polymer: Polymer) {
        polymerRepository.save(polymer)
    }

    override fun deletePolymer(id: Long) {
        polymerRepository.deleteById(id)
    }

    override fun addBlends(id: Long, blends: Set<Long>) {
        polymerRepository.findById(id).ifPresent {
            val set = arrayListOf<Polymer>()
            blends.forEach { id ->
                polymerRepository.findById(id).ifPresent { polymer ->
                    set.add(polymer)
                    val back = arrayListOf<Polymer>()
                    back.addAll(polymer.polymers.asIterable())
                    back.add(it)
                    polymer.polymers = back
                    polymerRepository.save(polymer)
                }
            }
            it.polymers = set
            polymerRepository.save(it)
        }
    }

    override fun updatePolymer(polymer: Polymer) {
        polymerRepository.findById(polymer.id).ifPresent {
            polymerRepository.save(polymer)
        }
    }

    override fun allPolymer(): List<Polymer> {
        return polymerRepository.findAll()
    }

    override fun getPolymerById(id: Long): Optional<Polymer> {
        return polymerRepository.findById(id)
    }

    override fun getBlendsById(id: Long): List<Polymer> {
        val set = ArrayList<Polymer>()
        polymerRepository.findById(id).ifPresent {
            it.polymers?.let { array ->
                set.addAll(array)
            }
        }
        return set
    }
}