package net.lz1998.pbbot.utils

import net.lz1998.pbbot.alias.MessageChain
import net.lz1998.pbbot.bot.Bot
import onebot.OnebotBase

open class Msg {
    open val messageChain: MutableList<OnebotBase.Message> = mutableListOf()

    companion object {
        @JvmStatic
        fun builder(): Msg {
            return Msg()
        }
    }

    fun text(text: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("text").putAllData(hashMapOf("text" to text)).build()
        )
        return this
    }

    fun image(url: String): Msg {
        messageChain.add(OnebotBase.Message.newBuilder().setType("image").putAllData(mapOf("url" to url)).build())
        return this
    }

    fun record(url: String): Msg {
        messageChain.add(OnebotBase.Message.newBuilder().setType("record").putAllData(mapOf("url" to url)).build())
        return this
    }

    fun flash(url: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("image").putAllData(mapOf("url" to url, "type" to "flash")).build()
        )
        return this
    }

    fun face(id: Int): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("face").putAllData(mapOf("id" to id.toString())).build()
        )
        return this
    }

    fun at(qq: Long): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("at").putAllData(mapOf("qq" to qq.toString())).build()
        )
        return this
    }

    fun atAll(): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("at").putAllData(mapOf("qq" to "all")).build()
        )
        return this
    }

    fun share(url: String, title: String, content: String, image: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("share")
                .putAllData(mapOf("url" to url, "title" to title, "content" to content, "image" to image)).build()
        )
        return this
    }

    fun build(): MessageChain {
        return messageChain
    }

    fun sendToGroup(bot: Bot, groupId: Long): Msg {
        bot.sendGroupMsg(groupId, this)
        return this
    }

    fun sendToFriend(bot: Bot, userId: Long): Msg {
        bot.sendPrivateMsg(userId, this)
        return this
    }
}