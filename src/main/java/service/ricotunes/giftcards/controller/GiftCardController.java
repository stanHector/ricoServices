package service.ricotunes.giftcards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.GiftCardDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.GiftCard;
import service.ricotunes.giftcards.model.Users;
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
    List<GiftCard> getCards() {
        return giftCardRepository.findAll();
    }

    //get user by Id
    @GetMapping("card/{id}")
    public ResponseEntity<GiftCard> getUserById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        GiftCard giftCard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id: " + id));
        return ResponseEntity.ok().body(giftCard);
    }


    @PostMapping("card")
    ResponseEntity<GiftCard> createCard(@Valid @RequestBody GiftCard giftCard) {
        return new ResponseEntity<>(giftCardRepository.save(giftCard), HttpStatus.OK);
    }


    @PutMapping("card/{id}")
    public GiftCard updateCard(@PathVariable("id") Long id, @Valid @RequestBody GiftCardDto giftCardDto) throws ResourceNotFoundException {
        System.out.println("Update Card with ID = " + id + "...");
        GiftCard giftcard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id: " + id));
        giftcard.setName(giftCardDto.getName());
        giftcard.setType(giftCardDto.getType());
        giftcard.setCategory(giftCardDto.getCategory());
        giftcard.setAmount(giftCardDto.getAmount());
        final GiftCard updatedGiftCard = giftCardRepository.save(giftcard);
        System.out.println("Updated Card " + updatedGiftCard);
        return giftCardRepository.save(updatedGiftCard);
    }

    //delete user
    @DeleteMapping("card/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        GiftCard giftcard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id: " + id));
        giftCardRepository.delete(giftcard);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
