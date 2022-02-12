package app.sosocom.smallstep.util

import app.sosocom.smallstep.BuildConfig

/**
 * 로그 태그
 * 상황 별 Flow 파악하기 위해 태그 구분
 */
enum class LogTag {
    LIFE_CYCLE,             // 생명주기
    USER                    // 사용자 이벤트 (ex : 클릭 등)
}

/**
 * 로그
 * @author 강승호 (sosocom95@gmail.com)
 * @since 2022.02.12
 */
object Log {
    /**
     * 로그 레벨
     */
    enum class Level {
        VERBOSE, DEBUG, INFO, WARN, ERROR
    }

    private const val TAG = "SMST"                      // Logcat에서 확인하기 위한 태그
    private val enabled = BuildConfig.DEBUG             // 동작 여부

    private const val MIN_STACK_OFFSET = 7              // 최소 Stack 오프셋

    /**
     * 상세 설명 로그
     */
    fun v(tag: LogTag, message: String) {
        if(enabled) log(Level.VERBOSE, tag, message)
    }

    /**
     * 상태 확인 로그
     */
    fun d(tag: LogTag, message: String) {
        if(enabled) log(Level.DEBUG, tag, message)
    }

    /**
     * 정보 로그
     */
    fun i(tag: LogTag, message: String) {
        if(enabled) log(Level.INFO, tag, message)
    }

    /**
     * 경고 로그
     */
    fun w(tag: LogTag, message: String = "", throwable: Throwable? = null) {
        if(enabled) log(Level.WARN, tag, message, throwable)
    }

    /**
     * 에러 로그
     */
    fun e(tag: LogTag, message: String = "", throwable: Throwable? = null) {
        if(enabled) log(Level.VERBOSE, tag, message, throwable)
    }

    /**
     * 레벨, 태그 별 로그 표시
     */
    private fun log(level: Level, tag: LogTag, message: String, throwable: Throwable? = null) {
        val logTag = "${TAG}_${tag.name}"

        val messageBuilder = StringBuilder()
        messageBuilder.append(getStackTraceString())
        messageBuilder.append(" ▶ ")
        messageBuilder.append(message)

        if(throwable != null)
            messageBuilder.append("\n${throwable.stackTraceToString()}")

        val msg = messageBuilder.toString()

        when(level) {
            Level.VERBOSE   -> android.util.Log.v(logTag, msg)
            Level.DEBUG     -> android.util.Log.d(logTag, msg)
            Level.INFO      -> android.util.Log.i(logTag, msg)
            Level.WARN      -> android.util.Log.w(logTag, msg)
            Level.ERROR     -> android.util.Log.e(logTag, msg)
        }
    }

    /**
     * 로그를 생성한 클래스, 라인 수를 반환
     */
    private fun getStackTraceString(): String {
        val trace = getStackTraceElement() ?: return ""

        return "(${trace.fileName}:${trace.lineNumber})"
    }

    /**
     * 로그를 생성한 StackTraceElement 가져오기
     */
    private fun getStackTraceElement(): StackTraceElement? {
        val trace = Thread.currentThread().stackTrace

        for(i in MIN_STACK_OFFSET .. trace.size) {
            if(trace[i].className != this::class.java.name)
                return trace[i]
        }

        return null
    }
}