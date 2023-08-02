package com.example.validationworkflow.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.validationworkflow.Repository.DomainRepository;
import com.example.validationworkflow.entities.Domain;
import com.example.validationworkflow.entities.Totals;

@RunWith(MockitoJUnitRunner.class)
public class DomainServiceTest {

    @Mock
    private DomainRepository domainRepository;

    @InjectMocks
    private DomainService domainService;

    private Domain domain1;
    private Domain domain2;

    @Before
    public void setUp() {
        domain1 = new Domain(1L, "example1.com", 100, 50, 150);
        domain2 = new Domain(2L, "example2.com", 200, 75, 250);
    }

    @Test
    public void testGetDomainById() {
        // Arrange
        Long domainId = 1L;
        when(domainRepository.findById(domainId)).thenReturn(Optional.of(domain1));

        // Act
        Domain resultDomain = domainService.getDomainById(domainId);

        // Assert
        assertNotNull(resultDomain);
        assertEquals(domainId, resultDomain.getId());
        assertEquals("example1.com", resultDomain.getDomainName());
        assertEquals(100, resultDomain.getAchievedLastYear());
        assertEquals(50, resultDomain.getContract());

        // Verify that the repository method was called with the correct ID
        verify(domainRepository, times(1)).findById(domainId);
    }

    @Test
    public void testGetAllDomains() {
        // Arrange
        List<Domain> mockDomains = Arrays.asList(domain1, domain2);
        when(domainRepository.findAll()).thenReturn(mockDomains);

        // Act
        List<Domain> resultDomains = domainService.getAllDomains();

        // Assert
        assertNotNull(resultDomains);
        assertEquals(2, resultDomains.size());

        // Verify that the repository method was called once
        verify(domainRepository, times(1)).findAll();
    }

    @Test
    public void testCreateDomain() {
        // Arrange
        Domain newDomain = new Domain(null, "newdomain.com", 300, 100, 400);
        Domain savedDomain = new Domain(3L, "newdomain.com", 300, 100, 400);
        when(domainRepository.save(newDomain)).thenReturn(savedDomain);

        // Act
        Domain resultDomain = domainService.createDomain(newDomain);

        // Assert
        assertNotNull(resultDomain);
        assertNotNull(resultDomain.getId());
        assertEquals("newdomain.com", resultDomain.getDomainName());
        assertEquals(300, resultDomain.getAchievedLastYear());
        assertEquals(100, resultDomain.getContract());

        // Verify that the repository method was called once with the newDomain object
        verify(domainRepository, times(1)).save(newDomain);
    }

    @Test
    public void testCalculateTotals() {
        // Arrange
        List<Domain> mockDomains = Arrays.asList(domain1, domain2);
        when(domainRepository.findAll()).thenReturn(mockDomains);

        // Act
        Totals resultTotals = domainService.calculateTotals();

        // Assert
        assertNotNull(resultTotals);
        assertEquals(400, resultTotals.getTotalAchievedThisYear());
        assertEquals(125, resultTotals.getTotalContract());
        assertEquals(300, resultTotals.getTotalAchievedLastYear());

        // Verify that the repository method was called once
        verify(domainRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateDomain() {
        // Arrange
        Long domainId = 1L;
        Domain updatedDomain = new Domain(domainId, "updateddomain.com", 150, 75, 225);
        when(domainRepository.findById(domainId)).thenReturn(Optional.of(domain1));
        when(domainRepository.save(domain1)).thenReturn(updatedDomain);

        // Act
        Domain resultDomain = domainService.updateDomain(domainId, updatedDomain);

        // Assert
        assertNotNull(resultDomain);
        assertEquals(domainId, resultDomain.getId());
        assertEquals("updateddomain.com", resultDomain.getDomainName());
        assertEquals(150, resultDomain.getAchievedLastYear());
        assertEquals(75, resultDomain.getContract());

        // Verify that the repository method was called once with the updatedDomain object
        verify(domainRepository, times(1)).save(domain1);
    }

    @Test
    public void testDeleteDomain() {
        // Arrange
        Long domainId = 1L;
        when(domainRepository.findById(domainId)).thenReturn(Optional.of(domain1));

        // Act
        boolean isDeleted = domainService.deleteDomain(domainId);

        // Assert
        assertTrue(isDeleted);

        // Verify that the repository method was called once with the correct ID
        verify(domainRepository, times(1)).findById(domainId);
        verify(domainRepository, times(1)).delete(domain1);
    }

    @Test
    public void testCalculateBillable() {
        // Arrange
        List<Domain> mockDomains = Arrays.asList(domain1, domain2);
        when(domainRepository.findAll()).thenReturn(mockDomains);

        // Act
        int resultBillable = domainService.calculateBillable();

        // Assert
        assertEquals(275, resultBillable);
    }
}
