package com.kleknerpolymer.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "Polymer")
data class Polymer(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        @Column(name = "full_name")
        var fullName: String,
        @Column(name = "short_name")
        var shortName: String,
        @Column(name = "cas_number")
        var casNumber: String,
        @Column(name = "method_synthesis", columnDefinition = "text")
        var methodSynthesis: String? = null,
        @Column(name = "density_min")
        var densityMin: Double? = null,
        @Column(name = "density_max")
        var densityMax: Double? = null,
        @Column(name = "melt_min")
        var meltMin: Int? = null,
        @Column(name = "melt_max")
        var meltMax: Int? = null,
        @Column(name = "max_service_temp")
        var maxServiceTemp: Int? = null,
        @Column(name = "color")
        var color: String? = null,
        @Column(name = "monomer")
        var monomersStructure: String? = null,
        @JsonIgnore
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(name = "polymer_blends",
                joinColumns = [JoinColumn(name = "polymer_id")],
                inverseJoinColumns = [JoinColumn(name = "blends_id")])
        var polymers: List<Polymer> = ArrayList(),
        @JsonIgnore
        @ManyToMany(mappedBy = "polymers")
        var blends: List<Polymer> = ArrayList()
)