package org.fa.blogmultiplatform.api

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import com.mongodb.reactivestreams.client.MongoCollection
import com.mongodb.reactivestreams.client.MongoDatabase

object BlogDB {
    private var mClient: MongoClient? = null
    private var mDatabase: MongoDatabase? = null

    private const val CONN_URL = "mongodb://localhost:27017/"
    private const val DB_NAME = "BlogDB"
    const val USERS = "Users"

    private fun getClient(): MongoClient {
        if (mClient == null) {
            mClient = MongoClients.create(CONN_URL)
        }

        return mClient!!
    }

    private fun getDatabase(): MongoDatabase {
        if (mDatabase == null) {
            mDatabase = getClient().getDatabase(DB_NAME)
        }

        return mDatabase!!
    }

    fun <T> getCollection(collectionName: String, type: Class<T>) : MongoCollection<T> = getDatabase().getCollection(collectionName, type)
}