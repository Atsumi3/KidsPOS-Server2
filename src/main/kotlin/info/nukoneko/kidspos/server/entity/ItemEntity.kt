package info.nukoneko.kidspos.server.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "item")
data class ItemEntity(@Id val id: Int = 0,
                      @Column(unique = true) val barcode: String = "",
                      val name: String = "",
                      val price: Int = 0)