package me.heyner.manusim.core.persistence

import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.Transient
import org.springframework.data.domain.Persistable

@MappedSuperclass
abstract class AbstractPersistableEntity<ID : Any> : Persistable<ID> {
    protected abstract var entityId: ID

    @Transient
    private var isNew: Boolean = true

    override fun getId(): ID = entityId

    override fun isNew(): Boolean = isNew

    @PostLoad
    @PostPersist
    fun markAsNotNew() {
        isNew = false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as AbstractPersistableEntity<*>
        return entityId == other.entityId
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
