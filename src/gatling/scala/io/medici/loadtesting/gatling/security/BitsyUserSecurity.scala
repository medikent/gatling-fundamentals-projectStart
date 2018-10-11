package io.medici.loadtesting.gatling.security

import org.bitcoinj.core.Address
import org.bitcoinj.params.AbstractBitcoinNetParams
import org.bitcoinj.wallet.Wallet

object BitsyUserSecurity {
  def generateRegistrationMessage(wallet: Wallet, networkParameters: AbstractBitcoinNetParams, userId: String, email: String, deviceId: String): BitsyUserMessage = {
    val key = wallet.currentReceiveKey()
    val address = new Address(networkParameters, key.getPubKeyHash).toBase58
    val timestamp = System.currentTimeMillis() / 1000
    return new BitsyUserMessage(
      userId = userId,
      email = email,
      deviceId = deviceId,
      base58Address = address,
      signedMessage = key.signMessage(s"${userId}${deviceId}${address}${timestamp}"),
      timestamp = timestamp
    )
  }
}

class BitsyUserMessage(
  val userId: String,
  val email: String,
  val deviceId: String,
  val base58Address: String,
  val signedMessage: String,
  val timestamp: Long) {

  override def toString = s"BitsyUserMessage($userId, $email, $deviceId, $base58Address, $signedMessage, $timestamp)"
}
