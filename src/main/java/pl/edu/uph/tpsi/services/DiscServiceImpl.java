package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.dto.DiscDTO;
import pl.edu.uph.tpsi.exceptions.ItemOutOfStockException;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.repositories.DiscRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author Grzegorz Piłat
 */
@Service ("discService")
public class DiscServiceImpl implements DiscService
{
        private final DiscRepository discRepository;

        @Autowired
        public DiscServiceImpl ( DiscRepository discRepository )
        {
                this.discRepository = discRepository;
        }

        /**
         * Finds all discs stored in database
         * @return list of discs mapped to DTO
         */
        @Override
        public List<DiscDTO> findAll ()
        {
                return discRepository.findAll()
                        .stream()
                        .map( DiscDTO::new )
                        .collect( Collectors.toList() );
        }

        /**
         * Retrieves disc from input ID argument
         * ID argument must be positive.
         * In case of negative number argument, returns
         * disc with id 0
         * @param id    id argument
         * @return      disc mapped to DTO
         */
        @Override
        public DiscDTO findById ( Long id )
        {
                if ( id != null && id < 0 )
                        return discRepository.findById( 0L )
                                .map( DiscDTO::new )
                                .orElse( null );
                return discRepository.findById( Objects.requireNonNull( id ) )
                        .map( DiscDTO::new )
                        .orElse( null );
        }

        /**
         * Creates new disc from input argument
         *
         * @param disc  disc argument that will be saved to database
         * @return      ID of saved disc
         */
        @Override
        public Long create ( DiscDTO disc )
        {
                return discRepository.save(
                        Disc.builder()
                                .band( disc.getBand() )
                                .amount( disc.getAmount() )
                                .price( disc.getPrice() )
                                .releaseDate( disc.getReleaseDate() )
                                .title( disc.getTitle() )
                                .deleted( false )
                                .images( disc.getImages() )
                                .description( disc.getDescription() )
                                .build()
                ).getID();
        }

        /**
         * Updates disc stored in DB with given argument
         * @param id    the ID of disc that will be updated
         * @param disc  argument that will be used to replace currently existing disc
         * @return      same object that is passed as argument
         */
        @Override
        public DiscDTO update ( Long id, DiscDTO disc )
        {
                Disc discToReplace = discRepository.findById( id ).orElse( null );
                if ( discToReplace != null )
                {
                        discToReplace.setAmount( disc.getAmount() );
                        discToReplace.setBand( disc.getBand() );
                        discToReplace.setDeleted( disc.getDeleted() );
                        discToReplace.setPrice( disc.getPrice() );
                        discToReplace.setReleaseDate( disc.getReleaseDate() );
                        discToReplace.setImages( disc.getImages() );
                        discToReplace.setDescription( disc.getDescription() );
                        discRepository.save( discToReplace );
                }
                return disc;
        }

        /**
         * Removes disc from database
         * @param id    the ID of disc that has to be removed
         * @return      true if disc has been removed successfully,
         *              false if disc does not exists
         */
        @Override
        public boolean delete ( Long id )
        {
                if ( !discRepository.existsById( id ) )
                        return false;
                discRepository.deleteById( id );
                return true;
        }

        /**
         * Checks if every disc's amount in order
         * is greater than actual amount of each disc in DB
         * @throws ItemOutOfStockException if any amount in order is not correct
         * with list of incorrect items
         * @param order the order which contains list of disc and amounts
         */
        @Override
        public void validateOrder ( Order order )
        {
                List<String> list = order.getDiscs()
                        .stream()
                        .filter( e -> e.getAmount() > discRepository.findById( e.getDisc().getID() ).get().getAmount() )
                        .map( e -> e.getDisc().getBand() + " " + e.getDisc().getTitle() )
                        .collect( Collectors.toList() );
                if ( !list.isEmpty() )
                        throw new ItemOutOfStockException( list );
        }

        /**
         * Decreases actual amount of each disc from DB in given order as argument
         * @param order the order that contains list of items
         */
        @Override
        public void decreaseAmount ( Order order )
        {
                order.getDiscs()
                        .forEach( e -> {
                                Disc disc = discRepository.findById( e.getDisc().getID() ).get();
                                disc.setAmount( disc.getAmount() - e.getAmount() );
                                discRepository.save( disc );
                        } );
        }

}
