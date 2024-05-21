package com.wahidabd.onelibrary.data.firebase.storage

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.firebase.OneFirebaseFirestore
import com.wahidabd.library.utils.firebase.OneFirebaseStorage
import com.wahidabd.onelibrary.data.firebase.storage.model.StorageRequest
import com.wahidabd.onelibrary.data.firebase.storage.model.StorageResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


class FirebaseStorageDataStore : FirebaseStorageRepository, OneFirebaseStorage, OneFirebaseFirestore() {

    override val databaseRef: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val storageReference: FirebaseStorage = FirebaseStorage.getInstance()
    override val storage: String = "files"

    override suspend fun getFiles(): Flow<Resource<List<StorageResponse>>> {
        return callbackFlow {
            getListValue(
                collection = "storages",
                clazz = StorageResponse::class.java,
            ) { listener ->
                trySend(listener)
            }

            awaitClose(this::close)
        }
    }

    override suspend fun uploadFile(body: StorageRequest): Flow<Resource<Boolean>> {
        return callbackFlow {
            pushFile(file = body.file, child = body.name) { url ->
                // set value to firestore after file uploaded
                body.url = url
                setValue(
                    collection = "storages",
                    value = body.toHashMap(),
                ) { listener ->
                    trySend(listener)
                }
            }

            awaitClose(this::close)
        }
    }

    override suspend fun deleteFile(id: String): Flow<Resource<Boolean>> {
        return callbackFlow {
            removeFile(id) { deleted ->
                if (deleted) {
                    deleteValue(collection = "storages", id = id) { listener ->
                        trySend(listener)
                    }
                }
            }

            awaitClose(this::close)
        }
    }
}