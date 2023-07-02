package com.wahidabd.library.utils.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.constant.OneConstant
import com.wahidabd.library.utils.coroutine.handler.GenericResponse


/**
 * Created by Wahid on 6/26/2023.
 * Github github.com/wahidabd.
 */


abstract class FirebaseRealtimeManager {
    protected abstract val databaseRef: DatabaseReference

    fun setValue(
        value: HashMap<String, Any>,
        child: String,
        eventListener: ((data: Resource<GenericResponse>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef.child(child).setValue(value)
            .addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(
                        GenericResponse(
                            true,
                            OneConstant.MESSAGE_SUCCESS_WRITE
                        )
                    )
                )
            }
            .addOnFailureListener { error ->
                eventListener.invoke(Resource.fail(error.localizedMessage))
            }
    }

    fun updateChildren(
        value: HashMap<String, Any>,
        child: String,
        eventListener: ((data: Resource<GenericResponse>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef.child(child).updateChildren(value)
            .addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(
                        GenericResponse(
                            true,
                            OneConstant.MESSAGE_SUCCESS_WRITE
                        )
                    )
                )
            }
            .addOnFailureListener { error ->
                eventListener.invoke(Resource.fail(error.localizedMessage))
            }
    }

    fun removeValue(
        child: String,
        eventListener: ((data: Resource<GenericResponse>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef.child(child).removeValue()
            .addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(
                        GenericResponse(
                            true,
                            OneConstant.MESSAGE_SUCCESS_WRITE
                        )
                    )
                )
            }
            .addOnFailureListener { error ->
                eventListener.invoke(Resource.fail(error.localizedMessage))
            }
    }

    fun <T> addListValueEventListener(
        clazz: Class<T>,
        child: String,
        eventListener: ((data: Resource<List<T>>) -> Unit),
    ) {
        eventListener.invoke(Resource.loading())
        try {
            databaseRef.child(child).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = ArrayList<T>()

                    for (i in snapshot.children) {
                        val data = i.getValue(clazz)
                        if (data != null) list.add(data)
                    }

                    if (list.isEmpty()) eventListener.invoke(Resource.empty())
                    else eventListener.invoke(Resource.success(list))
                }

                override fun onCancelled(error: DatabaseError) {
                    eventListener.invoke(Resource.fail(error.message))
                }
            })
        } catch (e: Exception) {
            eventListener.invoke(Resource.fail(e.localizedMessage))
        }
    }

    fun <T> addValueEventListener(
        clazz: Class<T>,
        child: String,
        eventListener: ((data: Resource<T>) -> Unit),
    ) {
        eventListener.invoke(Resource.loading())
        try {
            databaseRef.child(child).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val result = snapshot.getValue(clazz)
                    if (result != null) {
                        eventListener.invoke(Resource.success(result))
                    } else {
                        eventListener.invoke(Resource.fail(OneConstant.MESSAGE_DATA_NOT_FOUND))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    eventListener.invoke(Resource.fail(error.message))
                }

            })
        } catch (e: Exception) {
            eventListener.invoke(Resource.fail(e.localizedMessage))
        }
    }


}