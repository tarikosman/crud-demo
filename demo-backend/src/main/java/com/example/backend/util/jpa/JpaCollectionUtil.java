package com.example.backend.util.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.backend.util.common.Identifiable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JpaCollectionUtil {
    public static <E extends Identifiable, P extends Identifiable> void shallowUpdateEntitiesFromEvents(
            Collection<E> sources,
            Collection<P> targets,
            Function<UUID, P> provider) {
        Set<UUID> containedIds = sources.stream()
                .map(Identifiable::getId)
                .collect(Collectors.toSet());
        targets.removeIf(a -> !containedIds.remove(a.getId()));
        containedIds.stream().map(provider).forEach(targets::add);
    }

    public static <E extends Identifiable, P extends Identifiable> void deepUpdateEntitiesFromEvents(
            Collection<E> sources,
            Collection<P> targets,
            Function<E, P> toP,
            BiConsumer<E, P> updatePFromE) {
        List<P> add = new ArrayList<>();
        Map<UUID, E> updates = new HashMap<>();
        sources.stream().forEach(a -> {
            if (a.getId() == null) {
                add.add(toP.apply(a));
            } else {
                updates.put(a.getId(), a);
            }
        });
        Iterator<P> addressIterator = targets.iterator();
        while (addressIterator.hasNext()) {
            P a = addressIterator.next();
            var update = updates.get(a.getId());
            if (update == null) {
                addressIterator.remove();
            } else {
                updatePFromE.accept(update, a);
            }
        }
        targets.addAll(add);
    }
}
