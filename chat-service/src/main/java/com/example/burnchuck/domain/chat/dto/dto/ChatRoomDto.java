package com.example.burnchuck.domain.chat.dto.dto;

import com.example.burnchuck.common.entity.ChatRoom;
import com.example.burnchuck.common.enums.RoomType;
import com.example.burnchuck.domain.entity.ChatMessage;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomDto {

    private final Long roomId;
    private final String name;
    private final RoomType roomType;
    private final String chatRoomImg;
    private final String lastMessage;
    private final LocalDateTime lastMessageTime;
    private final int memberCount;
    private final Long unreadCount;

    public static ChatRoomDto of(ChatRoom room, String name, ChatMessage lastMsg, String chatRoomImg, int memberCount, Long unreadCount) {
        return new ChatRoomDto(
                room.getId(),
                name,
                room.getType(),
                chatRoomImg,
                lastMsg != null ? lastMsg.getContent() : null,
                lastMsg != null ? lastMsg.getCreatedDatetime() : room.getCreatedDatetime(),
                memberCount,
                unreadCount
        );
    }
}
