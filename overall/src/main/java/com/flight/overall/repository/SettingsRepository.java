package com.flight.overall.repository;

import com.flight.overall.entity.Settings;
import org.springframework.data.repository.CrudRepository;

/**
 * @author FLIGHT
 * @date 29.10.2022
 */
public interface SettingsRepository extends CrudRepository<Settings, Long> {
}
