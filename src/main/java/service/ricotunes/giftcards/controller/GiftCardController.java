package service.ricotunes.giftcards.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.GiftCardDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.GiftCard;
import service.ricotunes.giftcards.repository.GiftCardRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class GiftCardController {

    private GiftCardRepository giftCardRepository;

    public GiftCardController(GiftCardRepository giftCardRepository) {
        this.giftCardRepository = giftCardRepository;
    }


    @GetMapping("cards")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    List<GiftCard> getCards() {
        return giftCardRepository.findAll();
    }

    @PostMapping("card")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GiftCard> createCard(@RequestBody GiftCard card) {
        return new ResponseEntity<>(giftCardRepository.save(card), HttpStatus.OK);
    }


    @PatchMapping("card/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GiftCard updateCard(@PathVariable("id") long id, @Valid @RequestBody GiftCardDto cardDto) throws ResourceNotFoundException {
        System.out.println("Update Asset with Id = " + id + "...");
        GiftCard card = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id :: " + id));
        card.setType(cardDto.getType());
        card.setCategory(cardDto.getCategory());
        card.setAmount(cardDto.getAmount());
        final GiftCard updatedCard = giftCardRepository.save(card);
        System.out.println("Updated Card" + updatedCard);
        return giftCardRepository.save(updatedCard);

    }

    @DeleteMapping("card/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteCard(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        GiftCard card = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id :: " + id));
        giftCardRepository.delete(card);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
