package com.example.kotlin_samples.Repository

public class FetchRepository {

    companion object {
        var fetchRepository: FetchRepository? = null

        fun getFetchRepositoryInstance(): FetchRepository? {
            if (fetchRepository == null) {
                fetchRepository = FetchRepository()
            }
            return fetchRepository;
        }
    }


}