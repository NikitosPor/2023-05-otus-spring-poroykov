package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.helpers.IOStreamHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DisplayName("Class StudentCreationServiceImpl must be able to:")
class StudentCreationServiceImplTest {

    private static final String STUDENT_FULLNAME = "Ivan Drago";

    @Mock
    private IOStreamHelper ioStreamHelper;

    @InjectMocks
    private StudentCreationServiceImpl studentCreationService;

    @DisplayName("Create a student")
    @Test
    void getAllQuestionsAndAnswersTest() {
        when(ioStreamHelper.parseString()).thenReturn(STUDENT_FULLNAME);
        var testStudent = studentCreationService.askNameAndCreateStudent();
        assertEquals(testStudent.getFullName(), STUDENT_FULLNAME);
    }

}