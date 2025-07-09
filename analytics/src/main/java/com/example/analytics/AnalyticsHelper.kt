package com.example.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase



/**
 * Uygulama içi analiz olaylarını loglamak için genel bir arayüz.
 * Bu arayüz, belirli bir analiz sağlayıcısından (örn. Firebase) soyutlama sağlar.
 */
interface AnalyticsHelper {
    /**
     * Genel bir analiz olayı loglar.
     * @param eventName Olayın adı (örn: "button_click", "item_purchased").
     * @param params Olayla birlikte gönderilecek parametreler (isteğe bağlı).
     */
    fun logEvent(eventName: String, params: Map<String, String>? = null)

    /**
     * Kullanıcı ID'sini ayarlar.
     * @param userId Ayarlanacak kullanıcı ID'si. Null olursa kullanıcı ID'si kaldırılır.
     */
    fun setUserId(userId: String?)

    /**
     * Kullanıcı özelliğini ayarlar.
     * @param name Özelliğin adı.
     * @param value Özelliğin değeri.
     */
    fun setUserProperty(name: String, value: String?)

    /**
     * Belirli bir ekranın görüntülenmesini loglar.
     * @param screenName Görüntülenen ekranın adı.
     * @param screenClass Görüntülenen ekranın sınıf adı (isteğe bağlı, genellikle Composable adı).
     */
    fun logScreenView(screenName: String, screenClass: String? = null)

    /**
     * Giriş olayını loglar.
     * @param method Giriş yöntemi (örn: "Google", "Email", "Facebook").
     */
    fun logLogin(method: String)

    /**
     * Kayıt olayını loglar.
     * @param method Kayıt yöntemi.
     */
    fun logSignUp(method: String)

    /**
     * Bir arama olayını loglar.
     * @param searchTerm Aranılan terim.
     */
    fun logSearch(searchTerm: String)

    /**
     * İçerik görüntüleme olayını loglar.
     * @param contentType İçeriğin türü.
     * @param itemId İçeriğin benzersiz ID'si.
     * @param itemName İçeriğin adı.
     */
    fun logViewItem(contentType: String, itemId: String, itemName: String)

    // Uygulamanıza özel diğer analiz olaylarını da buraya ekleyebilirsiniz.
    // Örneğin: fun logAddToCart(itemId: String, quantity: Int)
}