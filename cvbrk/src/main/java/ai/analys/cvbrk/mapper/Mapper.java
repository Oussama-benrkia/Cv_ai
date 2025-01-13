package ai.analys.cvbrk.mapper;

public interface Mapper<E,R,Q> {
    E toEntity(Q query);
    R toResponse(E entity);
}
