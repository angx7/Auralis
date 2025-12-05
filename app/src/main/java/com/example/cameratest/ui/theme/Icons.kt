package com.example.cameratest.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


val EyeOpen: ImageVector
    get() {
        if (_EyeOpen != null) return _EyeOpen!!

        _EyeOpen = ImageVector.Builder(
            name = "EyeOpen",
            defaultWidth = 15.dp,
            defaultHeight = 15.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(7.5f, 11f)
                curveTo(4.80285f, 11f, 2.52952f, 9.62184f, 1.09622f, 7.50001f)
                curveTo(2.52952f, 5.37816f, 4.80285f, 4f, 7.5f, 4f)
                curveTo(10.1971f, 4f, 12.4705f, 5.37816f, 13.9038f, 7.50001f)
                curveTo(12.4705f, 9.62183f, 10.1971f, 11f, 7.5f, 11f)
                close()
                moveTo(7.5f, 3f)
                curveTo(4.30786f, 3f, 1.65639f, 4.70638f, 0.0760002f, 7.23501f)
                curveTo(-0.0253338f, 7.39715f, -0.0253334f, 7.60288f, 0.0760014f, 7.76501f)
                curveTo(1.65639f, 10.2936f, 4.30786f, 12f, 7.5f, 12f)
                curveTo(10.6921f, 12f, 13.3436f, 10.2936f, 14.924f, 7.76501f)
                curveTo(15.0253f, 7.60288f, 15.0253f, 7.39715f, 14.924f, 7.23501f)
                curveTo(13.3436f, 4.70638f, 10.6921f, 3f, 7.5f, 3f)
                close()
                moveTo(7.5f, 9.5f)
                curveTo(8.60457f, 9.5f, 9.5f, 8.60457f, 9.5f, 7.5f)
                curveTo(9.5f, 6.39543f, 8.60457f, 5.5f, 7.5f, 5.5f)
                curveTo(6.39543f, 5.5f, 5.5f, 6.39543f, 5.5f, 7.5f)
                curveTo(5.5f, 8.60457f, 6.39543f, 9.5f, 7.5f, 9.5f)
                close()
            }
        }.build()

        return _EyeOpen!!
    }

private var _EyeOpen: ImageVector? = null

val EyeClosed: ImageVector
    get() {
        if (_EyeClosed != null) return _EyeClosed!!

        _EyeClosed = ImageVector.Builder(
            name = "EyeClosed",
            defaultWidth = 15.dp,
            defaultHeight = 15.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(14.7649f, 6.07596f)
                curveTo(14.9991f, 6.22231f, 15.0703f, 6.53079f, 14.9239f, 6.76495f)
                curveTo(14.4849f, 7.46743f, 13.9632f, 8.10645f, 13.3702f, 8.66305f)
                lineTo(14.5712f, 9.86406f)
                curveTo(14.7664f, 10.0593f, 14.7664f, 10.3759f, 14.5712f, 10.5712f)
                curveTo(14.3759f, 10.7664f, 14.0593f, 10.7664f, 13.8641f, 10.5712f)
                lineTo(12.6011f, 9.30817f)
                curveTo(11.805f, 9.90283f, 10.9089f, 10.3621f, 9.93375f, 10.651f)
                lineTo(10.383f, 12.3277f)
                curveTo(10.4544f, 12.5944f, 10.2961f, 12.8685f, 10.0294f, 12.94f)
                curveTo(9.76267f, 13.0115f, 9.4885f, 12.8532f, 9.41704f, 12.5865f)
                lineTo(8.95917f, 10.8775f)
                curveTo(8.48743f, 10.958f, 8.00036f, 10.9999f, 7.50001f, 10.9999f)
                curveTo(6.99965f, 10.9999f, 6.51257f, 10.958f, 6.04082f, 10.8775f)
                lineTo(5.58299f, 12.5864f)
                curveTo(5.51153f, 12.8532f, 5.23737f, 13.0115f, 4.97064f, 12.94f)
                curveTo(4.7039f, 12.8686f, 4.5456f, 12.5944f, 4.61706f, 12.3277f)
                lineTo(5.06625f, 10.651f)
                curveTo(4.09111f, 10.3621f, 3.19503f, 9.90282f, 2.3989f, 9.30815f)
                lineTo(1.1359f, 10.5712f)
                curveTo(0.940638f, 10.7664f, 0.624058f, 10.7664f, 0.428798f, 10.5712f)
                curveTo(0.233537f, 10.3759f, 0.233537f, 10.0593f, 0.428798f, 9.86405f)
                lineTo(1.62982f, 8.66303f)
                curveTo(1.03682f, 8.10643f, 0.515113f, 7.46742f, 0.0760677f, 6.76495f)
                curveTo(-0.0702867f, 6.53079f, 0.000898544f, 6.22231f, 0.235065f, 6.07596f)
                curveTo(0.469231f, 5.9296f, 0.777703f, 6.00079f, 0.924058f, 6.23496f)
                curveTo(1.40354f, 7.00213f, 1.989f, 7.68057f, 2.66233f, 8.2427f)
                curveTo(2.67315f, 8.25096f, 2.6837f, 8.25972f, 2.69397f, 8.26898f)
                curveTo(4.00897f, 9.35527f, 5.65537f, 9.99991f, 7.50001f, 9.99991f)
                curveTo(10.3078f, 9.99991f, 12.6564f, 8.5063f, 14.076f, 6.23495f)
                curveTo(14.2223f, 6.00079f, 14.5308f, 5.9296f, 14.7649f, 6.07596f)
                close()
            }
        }.build()

        return _EyeClosed!!
    }

private var _EyeClosed: ImageVector? = null

val Bar_chart_4_bars: ImageVector
    get() {
        if (_Bar_chart_4_bars != null) return _Bar_chart_4_bars!!

        _Bar_chart_4_bars = ImageVector.Builder(
            name = "Bar_chart_4_bars",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(80f, 840f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(800f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(40f, -120f)
                verticalLineToRelative(-280f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(280f)
                close()
                moveToRelative(200f, 0f)
                verticalLineToRelative(-480f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(480f)
                close()
                moveToRelative(200f, 0f)
                verticalLineToRelative(-360f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(360f)
                close()
                moveToRelative(200f, 0f)
                verticalLineToRelative(-600f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(600f)
                close()
            }
        }.build()

        return _Bar_chart_4_bars!!
    }

private var _Bar_chart_4_bars: ImageVector? = null

val History: ImageVector
    get() {
        if (_History != null) return _History!!

        _History = ImageVector.Builder(
            name = "History",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(480f, 840f)
                quadToRelative(-138f, 0f, -240.5f, -91.5f)
                reflectiveQuadTo(122f, 520f)
                horizontalLineToRelative(82f)
                quadToRelative(14f, 104f, 92.5f, 172f)
                reflectiveQuadTo(480f, 760f)
                quadToRelative(117f, 0f, 198.5f, -81.5f)
                reflectiveQuadTo(760f, 480f)
                reflectiveQuadToRelative(-81.5f, -198.5f)
                reflectiveQuadTo(480f, 200f)
                quadToRelative(-69f, 0f, -129f, 32f)
                reflectiveQuadToRelative(-101f, 88f)
                horizontalLineToRelative(110f)
                verticalLineToRelative(80f)
                horizontalLineTo(120f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(94f)
                quadToRelative(51f, -64f, 124.5f, -99f)
                reflectiveQuadTo(480f, 120f)
                quadToRelative(75f, 0f, 140.5f, 28.5f)
                reflectiveQuadToRelative(114f, 77f)
                reflectiveQuadToRelative(77f, 114f)
                reflectiveQuadTo(840f, 480f)
                reflectiveQuadToRelative(-28.5f, 140.5f)
                reflectiveQuadToRelative(-77f, 114f)
                reflectiveQuadToRelative(-114f, 77f)
                reflectiveQuadTo(480f, 840f)
                moveToRelative(112f, -192f)
                lineTo(440f, 496f)
                verticalLineToRelative(-216f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(184f)
                lineToRelative(128f, 128f)
                close()
            }
        }.build()

        return _History!!
    }

private var _History: ImageVector? = null

val Perm_camera_mic: ImageVector
    get() {
        if (_Perm_camera_mic != null) return _Perm_camera_mic!!

        _Perm_camera_mic = ImageVector.Builder(
            name = "Perm_camera_mic",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(360f, 840f)
                horizontalLineTo(160f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(80f, 760f)
                verticalLineToRelative(-480f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(160f, 200f)
                horizontalLineToRelative(126f)
                lineToRelative(74f, -80f)
                horizontalLineToRelative(240f)
                lineToRelative(74f, 80f)
                horizontalLineToRelative(126f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(880f, 280f)
                verticalLineToRelative(480f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(800f, 840f)
                horizontalLineTo(600f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(-480f)
                horizontalLineTo(638f)
                lineToRelative(-73f, -80f)
                horizontalLineTo(395f)
                lineToRelative(-73f, 80f)
                horizontalLineTo(160f)
                verticalLineToRelative(480f)
                horizontalLineToRelative(200f)
                close()
                moveToRelative(80f, 0f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-124f)
                quadToRelative(86f, -14f, 143f, -80.5f)
                reflectiveQuadTo(720f, 480f)
                horizontalLineToRelative(-80f)
                quadToRelative(0f, 66f, -47f, 113f)
                reflectiveQuadToRelative(-113f, 47f)
                reflectiveQuadToRelative(-113f, -47f)
                reflectiveQuadToRelative(-47f, -113f)
                horizontalLineToRelative(-80f)
                quadToRelative(0f, 89f, 57f, 155.5f)
                reflectiveQuadTo(440f, 716f)
                close()
                moveToRelative(40f, -280f)
                quadToRelative(33f, 0f, 56.5f, -23.5f)
                reflectiveQuadTo(560f, 480f)
                verticalLineToRelative(-160f)
                quadToRelative(0f, -33f, -23.5f, -56.5f)
                reflectiveQuadTo(480f, 240f)
                reflectiveQuadToRelative(-56.5f, 23.5f)
                reflectiveQuadTo(400f, 320f)
                verticalLineToRelative(160f)
                quadToRelative(0f, 33f, 23.5f, 56.5f)
                reflectiveQuadTo(480f, 560f)
                moveTo(160f, 760f)
                horizontalLineToRelative(640f)
                close()
            }
        }.build()

        return _Perm_camera_mic!!
    }

private var _Perm_camera_mic: ImageVector? = null

val LightbulbSparkle: ImageVector
    get() {
        if (_LightbulbSparkle != null) return _LightbulbSparkle!!

        _LightbulbSparkle = ImageVector.Builder(
            name = "LightbulbSparkle",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(8.96712f, 9.60787f)
                curveTo(9.14342f, 9.26307f, 9.37775f, 8.94649f, 9.67076f, 8.65806f)
                curveTo(9.85736f, 8.47395f, 10.0257f, 8.28064f, 10.1757f, 8.07816f)
                curveTo(10.2158f, 7.96017f, 10.2532f, 7.8333f, 10.2873f, 7.69683f)
                curveTo(10.3615f, 7.40021f, 10.5382f, 7.15079f, 10.7739f, 6.98253f)
                curveTo(10.945f, 6.51978f, 11.0303f, 6.0252f, 11.0303f, 5.49953f)
                curveTo(11.0303f, 5.08664f, 10.9769f, 4.68802f, 10.8698f, 4.30397f)
                curveTo(10.763f, 3.92088f, 10.6112f, 3.56285f, 10.4143f, 3.23016f)
                curveTo(10.2177f, 2.89376f, 9.98251f, 2.5891f, 9.70882f, 2.31641f)
                curveTo(9.43513f, 2.0437f, 9.12939f, 1.80938f, 8.79183f, 1.61351f)
                curveTo(8.45803f, 1.41739f, 8.09883f, 1.2662f, 7.71451f, 1.15983f)
                curveTo(7.32923f, 1.0532f, 6.92934f, 1f, 6.51514f, 1f)
                curveTo(6.10094f, 1f, 5.70106f, 1.0532f, 5.31578f, 1.15983f)
                curveTo(4.93146f, 1.2662f, 4.56979f, 1.41764f, 4.23195f, 1.61364f)
                curveTo(3.89858f, 1.80953f, 3.59503f, 2.04383f, 3.32146f, 2.31641f)
                curveTo(3.04777f, 2.58911f, 2.81257f, 2.89377f, 2.61595f, 3.23018f)
                curveTo(2.41907f, 3.56286f, 2.26728f, 3.92089f, 2.16048f, 4.30397f)
                curveTo(2.05342f, 4.68802f, 2f, 5.08664f, 2f, 5.49953f)
                curveTo(2f, 6.11672f, 2.11756f, 6.69107f, 2.35361f, 7.22134f)
                curveTo(2.58896f, 7.75003f, 2.92468f, 8.22903f, 3.35953f, 8.65806f)
                curveTo(3.69832f, 8.99156f, 3.95683f, 9.36336f, 4.13553f, 9.77209f)
                curveTo(4.31772f, 10.1795f, 4.40927f, 10.622f, 4.40927f, 11.1009f)
                verticalLineTo(12.7012f)
                curveTo(4.40927f, 12.8807f, 4.44311f, 13.0503f, 4.51141f, 13.2091f)
                curveTo(4.57895f, 13.3661f, 4.67168f, 13.5038f, 4.78961f, 13.6213f)
                curveTo(4.90753f, 13.7388f, 5.04564f, 13.8311f, 5.20306f, 13.8984f)
                curveTo(5.36223f, 13.9663f, 5.53223f, 14f, 5.71205f, 14f)
                horizontalLineTo(7.31823f)
                curveTo(7.49806f, 14f, 7.66806f, 13.9663f, 7.82723f, 13.8984f)
                curveTo(7.98464f, 13.8311f, 8.12275f, 13.7388f, 8.24068f, 13.6213f)
                curveTo(8.35861f, 13.5038f, 8.45134f, 13.3661f, 8.51887f, 13.2091f)
                curveTo(8.58718f, 13.0503f, 8.62102f, 12.8807f, 8.62102f, 12.7012f)
                verticalLineTo(12.2734f)
                curveTo(8.61586f, 12.2723f, 8.61079f, 12.2712f, 8.60581f, 12.2701f)
                curveTo(8.54613f, 12.2576f, 8.50299f, 12.2525f, 8.48241f, 12.2506f)
                lineTo(8.47329f, 12.2498f)
                curveTo(8.1415f, 12.2429f, 7.8415f, 12.1066f, 7.62162f, 11.8894f)
                verticalLineTo(12.7012f)
                curveTo(7.62162f, 12.7823f, 7.59309f, 12.8512f, 7.5314f, 12.9127f)
                curveTo(7.46971f, 12.9741f, 7.40022f, 13.0028f, 7.31823f, 13.0028f)
                horizontalLineTo(5.71205f)
                curveTo(5.63007f, 13.0028f, 5.56058f, 12.9741f, 5.49888f, 12.9127f)
                curveTo(5.4372f, 12.8512f, 5.40867f, 12.7823f, 5.40867f, 12.7012f)
                verticalLineTo(10.5f)
                horizontalLineTo(7.35405f)
                curveTo(7.54359f, 10.0663f, 7.97213f, 9.76091f, 8.4732f, 9.75037f)
                lineTo(8.48232f, 9.7496f)
                curveTo(8.50289f, 9.74769f, 8.54604f, 9.74259f, 8.60572f, 9.73002f)
                curveTo(8.69843f, 9.7105f, 8.82494f, 9.67428f, 8.96712f, 9.60787f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFCC00)),
                strokeLineWidth = 0.75f,
                strokeLineJoin = StrokeJoin.Round,
                fill = SolidColor(Color.Black)
            ) {
                moveTo(11.5f, 14f)
                curveTo(12.25f, 11.0001f, 14.5f, 11.0001f, 14.5f, 11.0001f)
                curveTo(14.5f, 11.0001f, 12.25f, 11f, 11.5f, 8f)
                curveTo(10.75f, 11f, 8.5f, 11.0001f, 8.5f, 11.0001f)
                curveTo(8.5f, 11.0001f, 10.75f, 11f, 11.5f, 14f)
                close()
            }
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(12.926f, 13.2393f)
                curveTo(13.2849f, 12.977f, 13.5538f, 12.6663f, 13.7328f, 12.4216f)
                curveTo(13.8545f, 12.2553f, 14.1455f, 12.2553f, 14.2672f, 12.4216f)
                curveTo(14.4462f, 12.6663f, 14.7151f, 12.977f, 15.074f, 13.2393f)
                curveTo(15.2403f, 13.3609f, 15.2403f, 13.6393f, 15.074f, 13.7609f)
                curveTo(14.7151f, 14.0231f, 14.4462f, 14.3337f, 14.2672f, 14.5784f)
                curveTo(14.1455f, 14.7447f, 13.8545f, 14.7447f, 13.7328f, 14.5784f)
                curveTo(13.5538f, 14.3337f, 13.2849f, 14.0231f, 12.926f, 13.7609f)
                curveTo(12.7597f, 13.6393f, 12.7597f, 13.3609f, 12.926f, 13.2393f)
                close()
            }
        }.build()

        return _LightbulbSparkle!!
    }

private var _LightbulbSparkle: ImageVector? = null


val Target: ImageVector
    get() {
        if (_Target != null) return _Target!!

        _Target = ImageVector.Builder(
            name = "Target",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(480f, 880f)
                quadToRelative(-83f, 0f, -156f, -31.5f)
                reflectiveQuadTo(197f, 763f)
                reflectiveQuadToRelative(-85.5f, -127f)
                reflectiveQuadTo(80f, 480f)
                reflectiveQuadToRelative(31.5f, -156f)
                reflectiveQuadTo(197f, 197f)
                reflectiveQuadToRelative(127f, -85.5f)
                reflectiveQuadTo(480f, 80f)
                reflectiveQuadToRelative(156f, 31.5f)
                reflectiveQuadTo(763f, 197f)
                reflectiveQuadToRelative(85.5f, 127f)
                reflectiveQuadTo(880f, 480f)
                reflectiveQuadToRelative(-31.5f, 156f)
                reflectiveQuadTo(763f, 763f)
                reflectiveQuadToRelative(-127f, 85.5f)
                reflectiveQuadTo(480f, 880f)
                moveToRelative(0f, -80f)
                quadToRelative(134f, 0f, 227f, -93f)
                reflectiveQuadToRelative(93f, -227f)
                reflectiveQuadToRelative(-93f, -227f)
                reflectiveQuadToRelative(-227f, -93f)
                reflectiveQuadToRelative(-227f, 93f)
                reflectiveQuadToRelative(-93f, 227f)
                reflectiveQuadToRelative(93f, 227f)
                reflectiveQuadToRelative(227f, 93f)
                moveToRelative(0f, -80f)
                quadToRelative(-100f, 0f, -170f, -70f)
                reflectiveQuadToRelative(-70f, -170f)
                reflectiveQuadToRelative(70f, -170f)
                reflectiveQuadToRelative(170f, -70f)
                reflectiveQuadToRelative(170f, 70f)
                reflectiveQuadToRelative(70f, 170f)
                reflectiveQuadToRelative(-70f, 170f)
                reflectiveQuadToRelative(-170f, 70f)
                moveToRelative(0f, -80f)
                quadToRelative(66f, 0f, 113f, -47f)
                reflectiveQuadToRelative(47f, -113f)
                reflectiveQuadToRelative(-47f, -113f)
                reflectiveQuadToRelative(-113f, -47f)
                reflectiveQuadToRelative(-113f, 47f)
                reflectiveQuadToRelative(-47f, 113f)
                reflectiveQuadToRelative(47f, 113f)
                reflectiveQuadToRelative(113f, 47f)
                moveToRelative(0f, -80f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(400f, 480f)
                reflectiveQuadToRelative(23.5f, -56.5f)
                reflectiveQuadTo(480f, 400f)
                reflectiveQuadToRelative(56.5f, 23.5f)
                reflectiveQuadTo(560f, 480f)
                reflectiveQuadToRelative(-23.5f, 56.5f)
                reflectiveQuadTo(480f, 560f)
            }
        }.build()

        return _Target!!
    }

private var _Target: ImageVector? = null

val Nest_clock_farsight_analog: ImageVector
    get() {
        if (_Nest_clock_farsight_analog != null) return _Nest_clock_farsight_analog!!

        _Nest_clock_farsight_analog = ImageVector.Builder(
            name = "Nest_clock_farsight_analog",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(582f, 662f)
                lineTo(440f, 520f)
                verticalLineToRelative(-200f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(167f)
                lineToRelative(118f, 118f)
                close()
                moveTo(440f, 240f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(280f, 280f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                close()
                moveTo(440f, 800f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                close()
                moveTo(160f, 520f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                close()
                moveTo(480f, 880f)
                quadToRelative(-83f, 0f, -156f, -31.5f)
                reflectiveQuadTo(197f, 763f)
                reflectiveQuadToRelative(-85.5f, -127f)
                reflectiveQuadTo(80f, 480f)
                reflectiveQuadToRelative(31.5f, -156f)
                reflectiveQuadTo(197f, 197f)
                reflectiveQuadToRelative(127f, -85.5f)
                reflectiveQuadTo(480f, 80f)
                reflectiveQuadToRelative(156f, 31.5f)
                reflectiveQuadTo(763f, 197f)
                reflectiveQuadToRelative(85.5f, 127f)
                reflectiveQuadTo(880f, 480f)
                reflectiveQuadToRelative(-31.5f, 156f)
                reflectiveQuadTo(763f, 763f)
                reflectiveQuadToRelative(-127f, 85.5f)
                reflectiveQuadTo(480f, 880f)
                moveToRelative(0f, -80f)
                quadToRelative(134f, 0f, 227f, -93f)
                reflectiveQuadToRelative(93f, -227f)
                reflectiveQuadToRelative(-93f, -227f)
                reflectiveQuadToRelative(-227f, -93f)
                reflectiveQuadToRelative(-227f, 93f)
                reflectiveQuadToRelative(-93f, 227f)
                reflectiveQuadToRelative(93f, 227f)
                reflectiveQuadToRelative(227f, 93f)
                moveToRelative(0f, -320f)
            }
        }.build()

        return _Nest_clock_farsight_analog!!
    }

private var _Nest_clock_farsight_analog: ImageVector? = null

val Calendar_today: ImageVector
    get() {
        if (_Calendar_today != null) return _Calendar_today!!

        _Calendar_today = ImageVector.Builder(
            name = "Calendar_today",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(200f, 880f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(120f, 800f)
                verticalLineToRelative(-560f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(200f, 160f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(320f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(40f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(840f, 240f)
                verticalLineToRelative(560f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(760f, 880f)
                close()
                moveToRelative(0f, -80f)
                horizontalLineToRelative(560f)
                verticalLineToRelative(-400f)
                horizontalLineTo(200f)
                close()
                moveToRelative(0f, -480f)
                horizontalLineToRelative(560f)
                verticalLineToRelative(-80f)
                horizontalLineTo(200f)
                close()
                moveToRelative(0f, 0f)
                verticalLineToRelative(-80f)
                close()
            }
        }.build()

        return _Calendar_today!!
    }

private var _Calendar_today: ImageVector? = null

val BrainCircuit: ImageVector
    get() {
        if (_BrainCircuit != null) return _BrainCircuit!!

        _BrainCircuit = ImageVector.Builder(
            name = "BrainCircuit",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 5f)
                arcToRelative(3f, 3f, 0f, true, false, -5.997f, 0.125f)
                arcToRelative(4f, 4f, 0f, false, false, -2.526f, 5.77f)
                arcToRelative(4f, 4f, 0f, false, false, 0.556f, 6.588f)
                arcTo(4f, 4f, 0f, true, false, 12f, 18f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9f, 13f)
                arcToRelative(4.5f, 4.5f, 0f, false, false, 3f, -4f)
                moveTo(6.003f, 5.125f)
                arcTo(3f, 3f, 0f, false, false, 6.401f, 6.5f)
                moveToRelative(-2.924f, 4.396f)
                arcToRelative(4f, 4f, 0f, false, true, 0.585f, -0.396f)
                moveTo(6f, 18f)
                arcToRelative(4f, 4f, 0f, false, true, -1.967f, -0.516f)
                moveTo(12f, 13f)
                horizontalLineToRelative(4f)
                moveToRelative(-4f, 5f)
                horizontalLineToRelative(6f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
                verticalLineToRelative(1f)
                moveTo(12f, 8f)
                horizontalLineToRelative(8f)
                moveToRelative(-4f, 0f)
                verticalLineTo(5f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16.5f, 13f)
                arcTo(0.5f, 0.5f, 0f, false, true, 16f, 13.5f)
                arcTo(0.5f, 0.5f, 0f, false, true, 15.5f, 13f)
                arcTo(0.5f, 0.5f, 0f, false, true, 16.5f, 13f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(18.5f, 3f)
                arcTo(0.5f, 0.5f, 0f, false, true, 18f, 3.5f)
                arcTo(0.5f, 0.5f, 0f, false, true, 17.5f, 3f)
                arcTo(0.5f, 0.5f, 0f, false, true, 18.5f, 3f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(20.5f, 21f)
                arcTo(0.5f, 0.5f, 0f, false, true, 20f, 21.5f)
                arcTo(0.5f, 0.5f, 0f, false, true, 19.5f, 21f)
                arcTo(0.5f, 0.5f, 0f, false, true, 20.5f, 21f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(20.5f, 8f)
                arcTo(0.5f, 0.5f, 0f, false, true, 20f, 8.5f)
                arcTo(0.5f, 0.5f, 0f, false, true, 19.5f, 8f)
                arcTo(0.5f, 0.5f, 0f, false, true, 20.5f, 8f)
                close()
            }
        }.build()

        return _BrainCircuit!!
    }

private var _BrainCircuit: ImageVector? = null

val Soundwave: ImageVector
    get() {
        if (_Soundwave != null) return _Soundwave!!

        _Soundwave = ImageVector.Builder(
            name = "Soundwave",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(8.5f, 2f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(11f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineToRelative(-11f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                moveToRelative(-2f, 2f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(7f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineToRelative(-7f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                moveToRelative(4f, 0f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(7f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineToRelative(-7f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                moveToRelative(-6f, 1.5f)
                arcTo(0.5f, 0.5f, 0f, false, true, 5f, 6f)
                verticalLineToRelative(4f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineTo(6f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                moveToRelative(8f, 0f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(4f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineTo(6f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                moveToRelative(-10f, 1f)
                arcTo(0.5f, 0.5f, 0f, false, true, 3f, 7f)
                verticalLineToRelative(2f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineTo(7f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                moveToRelative(12f, 0f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(2f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineTo(7f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
            }
        }.build()

        return _Soundwave!!
    }

private var _Soundwave: ImageVector? = null

val TrendingUp: ImageVector
    get() {
        if (_TrendingUp != null) return _TrendingUp!!

        _TrendingUp = ImageVector.Builder(
            name = "TrendingUp",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveToRelative(22f, 7f)
                lineToRelative(-8.5f, 8.5f)
                lineToRelative(-5f, -5f)
                lineTo(2f, 17f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16f, 7f)
                horizontalLineToRelative(6f)
                verticalLineToRelative(6f)
            }
        }.build()

        return _TrendingUp!!
    }

private var _TrendingUp: ImageVector? = null



val Bullseye: ImageVector
    get() {
        if (_Bullseye != null) return _Bullseye!!

        _Bullseye = ImageVector.Builder(
            name = "Bullseye",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(8f, 15f)
                arcTo(7f, 7f, 0f, true, true, 8f, 1f)
                arcToRelative(7f, 7f, 0f, false, true, 0f, 14f)
                moveToRelative(0f, 1f)
                arcTo(8f, 8f, 0f, true, false, 8f, 0f)
                arcToRelative(8f, 8f, 0f, false, false, 0f, 16f)
            }
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(8f, 13f)
                arcTo(5f, 5f, 0f, true, true, 8f, 3f)
                arcToRelative(5f, 5f, 0f, false, true, 0f, 10f)
                moveToRelative(0f, 1f)
                arcTo(6f, 6f, 0f, true, false, 8f, 2f)
                arcToRelative(6f, 6f, 0f, false, false, 0f, 12f)
            }
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(8f, 11f)
                arcToRelative(3f, 3f, 0f, true, true, 0f, -6f)
                arcToRelative(3f, 3f, 0f, false, true, 0f, 6f)
                moveToRelative(0f, 1f)
                arcToRelative(4f, 4f, 0f, true, false, 0f, -8f)
                arcToRelative(4f, 4f, 0f, false, false, 0f, 8f)
            }
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(9.5f, 8f)
                arcToRelative(1.5f, 1.5f, 0f, true, true, -3f, 0f)
                arcToRelative(1.5f, 1.5f, 0f, false, true, 3f, 0f)
            }
        }.build()

        return _Bullseye!!
    }

private var _Bullseye: ImageVector? = null

val Disc: ImageVector
    get() {
        if (_Disc != null) return _Disc!!

        _Disc = ImageVector.Builder(
            name = "Disc",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(8f, 15f)
                arcTo(7f, 7f, 0f, true, true, 8f, 1f)
                arcToRelative(7f, 7f, 0f, false, true, 0f, 14f)
                moveToRelative(0f, 1f)
                arcTo(8f, 8f, 0f, true, false, 8f, 0f)
                arcToRelative(8f, 8f, 0f, false, false, 0f, 16f)
            }
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(10f, 8f)
                arcToRelative(2f, 2f, 0f, true, true, -4f, 0f)
                arcToRelative(2f, 2f, 0f, false, true, 4f, 0f)
                moveTo(8f, 4f)
                arcToRelative(4f, 4f, 0f, false, false, -4f, 4f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                arcToRelative(5f, 5f, 0f, false, true, 5f, -5f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0f, 1f)
                moveToRelative(4.5f, 3.5f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                arcToRelative(5f, 5f, 0f, false, true, -5f, 5f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0f, -1f)
                arcToRelative(4f, 4f, 0f, false, false, 4f, -4f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
            }
        }.build()

        return _Disc!!
    }

private var _Disc: ImageVector? = null

