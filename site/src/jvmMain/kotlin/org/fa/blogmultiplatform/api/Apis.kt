package org.fa.blogmultiplatform.api

import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import org.fa.blogmultiplatform.repositories.user.UserRepoImpl

@InitApi
fun initApi(context: InitApiContext) {
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )

    context.data.add(UserRepoImpl())
}