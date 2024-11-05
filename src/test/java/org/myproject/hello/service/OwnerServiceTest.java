package org.myproject.hello.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.myproject.hello.entity.Owner;
import org.myproject.hello.repository.OwnerRepository;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceTest {

    private static final Long OWNER_ID = 1L;
    private static final String OWNER_FIRSTNAME = "first";
    private static final String OWNER_LASTNAME = "last";
    private static final String OWNER_ADDRESS = "address";
    private static final String OWNER_CITY = "city";
    private static final String OWNER_TELEPHONE = "1234";
    private static final String ERROR_MESSAGE = "Owner does not exist";

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void findOwnersTest() {
        // given
        Owner owner = new Owner();
        owner.setId(OWNER_ID);
        owner.setFirstName(OWNER_FIRSTNAME);
        owner.setLastName(OWNER_LASTNAME);
        owner.setAddress(OWNER_ADDRESS);
        owner.setCity(OWNER_CITY);
        owner.setTelephone(OWNER_TELEPHONE);

        when(ownerRepository.findAll()).thenReturn(Arrays.asList(owner, owner));

        // when
        List<Owner> foundOwners = ownerService.findOwners();

        // then
        assertThat(foundOwners.size()).isEqualTo(2);
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findOwnersEmptyTest() {
        // given
        when(ownerRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        List<Owner> foundOwners = ownerService.findOwners();

        // then
        assertThat(foundOwners.size()).isEqualTo(0);
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findOwnerTest() {
        // given
        Owner owner = new Owner();
        owner.setId(OWNER_ID);
        owner.setFirstName(OWNER_FIRSTNAME);
        owner.setLastName(OWNER_LASTNAME);
        owner.setAddress(OWNER_ADDRESS);
        owner.setCity(OWNER_CITY);
        owner.setTelephone(OWNER_TELEPHONE);

        when(ownerRepository.findById(OWNER_ID)).thenReturn(Optional.of(owner));

        // when
        Owner foundOwner = ownerService.findOwner(OWNER_ID);

        // then
        assertThat(foundOwner.getId()).isEqualTo(OWNER_ID);
        assertThat(foundOwner.getFirstName()).isEqualTo(OWNER_FIRSTNAME);
        assertThat(foundOwner.getLastName()).isEqualTo(OWNER_LASTNAME);
        assertThat(foundOwner.getAddress()).isEqualTo(OWNER_ADDRESS);
        assertThat(foundOwner.getCity()).isEqualTo(OWNER_CITY);
        assertThat(foundOwner.getTelephone()).isEqualTo(OWNER_TELEPHONE);
        verify(ownerRepository, times(1)).findById(OWNER_ID);
    }

    @Test
    void findOwnerVerifyTest() {
        // given
        when(ownerRepository.findById(OWNER_ID)).thenReturn(Optional.empty());

        // when
        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> {
            ownerService.findOwner(OWNER_ID);
        });

        // then
        assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE);
        verify(ownerRepository, times(1)).findById(OWNER_ID);
    }
}