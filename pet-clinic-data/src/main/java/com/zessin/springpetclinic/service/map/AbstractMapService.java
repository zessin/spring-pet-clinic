package com.zessin.springpetclinic.service.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.zessin.springpetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

	protected Map<Long, T> map = new HashMap<>();

	public Set<T> findAll() {
		return new HashSet<>(map.values());
	}

	public T findById(ID id) {
		return map.get(id);
	}

	public T save(T object) {
		if (object != null) {
			if (object.getId() == null) {
				object.setId(getNextId());
			}

			map.put(object.getId(), object);
		} else {
			throw new RuntimeException("Object should not be null");
		}

		return object;
	}

	public void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}

	public void deleteById(ID id) {
		map.remove(id);
	}

	private Long getNextId() {
		if (map.isEmpty()) {
			return 1L;
		} else {
			return Collections.max(map.keySet()) + 1L;
		}
	}

}
