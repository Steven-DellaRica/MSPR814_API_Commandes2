package fr.epsi.apicommande.services;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

@Converter
public class UUIDConverter implements AttributeConverter<UUID, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(UUID uuid) {
        if (uuid == null) {
            return null;
        }

        System.out.println("Conversion UUID vers BINARY(16) : " + uuid);

        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());

        byte[] bytes = buffer.array();

        System.out.println("Bytes stockés en BDD : " + Arrays.toString(bytes));

        return bytes;
    }

    @Override
    public UUID convertToEntityAttribute(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        long mostSignificantBits = buffer.getLong();
        long leastSignificantBits = buffer.getLong();
        UUID uuid = new UUID(mostSignificantBits, leastSignificantBits);
        System.out.println("Conversion BINARY(16) vers UUID : " + uuid);

        return uuid;
    }
}
