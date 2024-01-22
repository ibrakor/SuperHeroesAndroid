package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.domain.left
import com.ibrakor.superheroes.app.domain.right
import com.ibrakor.superheroes.features.detail.domain.Images
import com.ibrakor.superheroes.features.detail.domain.PowerStats
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetSuperHeroesFeedUseCaseTest {
    @MockK
    private lateinit var superHeroRepository: SuperHeroRepository

    @MockK
    lateinit var workRepository: WorkRepository

    @MockK
    lateinit var biographyRepository: BiographyRepository

    private lateinit var useCase: GetSuperHeroesFeedUseCase

    @BeforeEach
    fun setup() {
        useCase =
            GetSuperHeroesFeedUseCase(superHeroRepository, workRepository, biographyRepository)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val superHero =
        SuperHero(1, "hola", Images("xs", "s", "m", "l"), "sadasd", PowerStats("10", "10", "5"))
    private val work = Work("job")
    val bio = Biography("klark", "neutral")

    @Test
    fun `WHEN obtain superhero is error Expect either with error`() = runTest {
        //Given
        coEvery { superHeroRepository.obtainSuperHeros() } returns ErrorApp.UnknownError.left()
        //When
        val result = useCase()
        //Then
        assert(result.isLeft())
    }

    @Test
    fun `WHEN obtain superhero is successful Expect either with superhero list`() = runTest {
        //Given


        val superHeroOutput = SuperHeroOutput(superHero, work, bio)

        coEvery { superHeroRepository.obtainSuperHeros() } returns listOf(superHero).right()
        coEvery { workRepository.obtainWork(superHero.id) } returns work.right()
        coEvery { biographyRepository.obtainBiography(superHero.id) } returns bio.right()

        //When
        val result = useCase()

        //Then
        assert(result.isRight())
        assertEquals(listOf(superHeroOutput), result.get())
    }

    @Test
    fun `WHEN obtain work is error Expect either with error`() = runTest {
        //Given

        coEvery { superHeroRepository.obtainSuperHeros() } returns listOf(superHero).right()
        coEvery { workRepository.obtainWork(superHero.id) } returns ErrorApp.UnknownError.left()
        coEvery { biographyRepository.obtainBiography(superHero.id) } returns bio.right()


        //When
        val result = useCase()

        //Then
        assert(result.isLeft())
    }

    @Test
    fun `WHEN obtain biography is error Expect either with error`() = runTest {
        //Given


        coEvery { superHeroRepository.obtainSuperHeros() } returns listOf(superHero).right()
        coEvery { workRepository.obtainWork(superHero.id) } returns work.right()
        coEvery { biographyRepository.obtainBiography(superHero.id) } returns ErrorApp.UnknownError.left()

        //When
        val result = useCase()

        //Then
        assert(result.isLeft())
    }
}