#version 300 es
//外部纹理
#extension GL_OES_EGL_image_external_essl3 : require
precision mediump float;
uniform samplerExternalOES uTextureSampler;

uniform float u_tex_height;
uniform float u_tex_width;
uniform float u_mos_square;

in vec2 vTextureCoord;
out vec4 vFragColor;



void main()
{

    vec2 TexSize = vec2(u_tex_height,u_tex_width);
    vec2 mosaicSize = vec2(u_mos_square,u_mos_square);



    vec2 maskXY = vec2(vTextureCoord.x * TexSize.x, vTextureCoord.y * TexSize.y);
    vec2 mosaicXY = vec2(floor(maskXY.x/mosaicSize.x)*mosaicSize.x,floor(maskXY.y/mosaicSize.y)*mosaicSize.y);

    vec2 newTextureCoord = vec2(mosaicXY.x/TexSize.x,mosaicXY.y/TexSize.y);


    //再做二值化
    //对xyz操作
    vec4 color = texture(uTextureSampler,newTextureCoord);
    //    黑白滤镜
    float gray =(0.299*color.r + 0.587*color.g + 0.184*color.b);

    if (gray > 0.5f){
        gray = 0.9f;
    } else {
        gray = 0.1f;
    }

    vFragColor = vec4(gray,gray,gray,1.0f);
}