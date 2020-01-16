package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uph.tpsi.dto.DiscDTO;
import pl.edu.uph.tpsi.exceptions.ItemOutOfStockException;
import pl.edu.uph.tpsi.mappers.DiscMapper;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.repositories.DiscRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author Grzegorz Piłat
 */
@Service("discService")
public class DiscServiceImpl implements DiscService {
    private final DiscRepository discRepository;

    private final DiscMapper discMapper;

    @Autowired
    public DiscServiceImpl(DiscRepository discRepository,
                           DiscMapper discMapper) {
        this.discRepository = discRepository;
        this.discMapper = discMapper;
    }

    /**
     * Finds all discs stored in database that are not deleted
     *
     * @return list of discs mapped to DTO
     */
    @Override
    public List<DiscDTO> findAll() {
        return discRepository.findAll()
                .stream()
                .filter(e -> !e.getDeleted())
                .map(discMapper::discToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves disc from input ID argument
     * ID argument must be positive.
     * In case of negative number argument, returns
     * disc with id 0
     *
     * @param id id argument
     * @return disc mapped to DTO
     */
    @Override
    public DiscDTO findById(Long id) {
        if (id != null && id < 0)
            return discRepository.findById(0L)
                    .map(discMapper::discToDTO)
                    .orElse(null);
        return discRepository.findById(Objects.requireNonNull(id))
                .map(discMapper::discToDTO)
                .orElse(null);
    }

    /**
     * Creates new disc from input argument
     *
     * @param disc disc argument that will be saved to database
     * @return ID of saved disc
     */
    @Override
    public Long create(DiscDTO disc) {
        disc.setDeleted(false);
        return discRepository.save(discMapper.DTOtoDisc(disc))
                .getID();
    }

    /**
     * Updates disc stored in DB with given argument
     *
     * @param id   the ID of disc that will be updated
     * @param disc argument that will be used to replace currently existing disc
     * @return same object that is passed as argument
     */
    @Override
    public DiscDTO update(Long id, DiscDTO disc) {
        discRepository.findById(id)
                .ifPresent(discToReplace -> discRepository
                        .save(discMapper.DTOtoDisc(disc)));
        return disc;
    }

    /**
     * Removes disc by setting it's delete flag to true
     *
     * @param id the ID of disc that has to be removed
     * @return true if disc has been removed successfully,
     * false if disc does not exists
     */
    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!discRepository.existsById(id))
            return false;
        Disc d = discRepository.getOne(id);
        d.setDeleted(true);
        discRepository.save(d);
        return true;
    }

    /**
     * Checks if every disc's amount in order
     * is greater than actual amount of each disc in DB
     *
     * @param order the order which contains list of disc and amounts
     * @throws ItemOutOfStockException if any amount in order is not correct
     *                                 with list of incorrect items
     */
    @Override
    public void validateOrder(Order order) {
        List<String> list = order.getDiscs()
                .stream()
                .filter(e -> e.getAmount() > discRepository.findById(e.getDisc().getID()).get().getAmount())
                .map(e -> e.getDisc().getBand() + " " + e.getDisc().getTitle())
                .collect(Collectors.toList());
        if (!list.isEmpty())
            throw new ItemOutOfStockException(list);
    }

    /**
     * Decreases actual amount of each disc from DB in given order as argument
     *
     * @param order the order that contains list of items
     */
    @Override
    public void decreaseAmount(Order order) {
        order.getDiscs()
                .forEach(e -> {
                    Disc disc = discRepository.findById(e.getDisc().getID()).get();
                    disc.setAmount(disc.getAmount() - e.getAmount());
                    discRepository.save(disc);
                });
    }

}
