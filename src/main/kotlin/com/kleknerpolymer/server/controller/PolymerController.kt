package com.kleknerpolymer.server.controller

import com.kleknerpolymer.server.entity.Polymer
import com.kleknerpolymer.server.service.PolymerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("polymers")
class PolymerController(private val polymerService: PolymerService) {

    @GetMapping
    fun getAllPolymers(): ResponseEntity<List<Polymer>> {
        return ResponseEntity.ok(polymerService.allPolymer())
    }

    @DeleteMapping("/{id}")
    fun deletePolymer(@PathVariable(value = "id") id: Long): ResponseEntity<HttpStatus> {
        return if (polymerService.getPolymerById(id).isPresent) {
            polymerService.deletePolymer(id)
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping
    fun updatePolymer(@RequestBody polymer: Polymer): ResponseEntity<HttpStatus> {
        polymerService.updatePolymer(polymer)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping
    fun addPolymer(@RequestBody polymer: Polymer): ResponseEntity<HttpStatus> {
        polymerService.addPolymer(polymer)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/{id}/blends")
    fun addBlends(@RequestBody blends: Set<Long>, @PathVariable(value = "id") id: Long): ResponseEntity<HttpStatus> {
        polymerService.addBlends(id, blends)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getPolymerById(@PathVariable(value = "id") id: Long): ResponseEntity<Polymer> {
        return ResponseEntity.of(polymerService.getPolymerById(id))
    }

    @GetMapping("/{id}/blends")
    fun getBlendsById(@PathVariable(value = "id") id: Long): List<Polymer> {
        return polymerService.getBlendsById(id)
    }
}